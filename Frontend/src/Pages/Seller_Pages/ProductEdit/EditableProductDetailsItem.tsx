import { MouseEvent, useEffect, useState } from "react";
import TextEditor from './TextEditor/TextEditor'
import 'bootstrap-icons/font/bootstrap-icons.css'
import'./EditableProductDetailsItem.css'
import '../../../Components/UI_Components/GH_Btn.css'

interface EditableProductDetailsItemProps{
    item: string;
    documentEdit: boolean;
    fontSize: string;
    onChange: (newText: string) => void;
}

const EditableProductDetailsItem = ({item, documentEdit, fontSize, onChange}: EditableProductDetailsItemProps) => {

    let [body, setBody] = useState(item);

    useEffect(() => {
        // Update the 'body' state when the 'item' prop changes
        setBody(item);
        setEditedText(item);
      }, [item]);
    
    const handleSaveChanges = (e:any) => {
        setEditActive(false);
        setBody(editedText);
    }
    
    const [editActive, setEditActive] = useState(false);
    
    const handleEditEvent = (e: any) => {
        setEditActive(true);
    }
    
    const [editedText, setEditedText] = useState(item);
    
    const handleTextChange = (e:any) => {
        setEditedText(e.target.value);
        onChange(e.target.value)
    };

    const handleDocBodyChange = (e:any) => {
        setEditedText(e);
        onChange(e)
    };


    return (
    <>
        {!editActive && 
            <div className="editable-product-details-item">
                <div style={{fontSize: `${fontSize}`}} dangerouslySetInnerHTML={{ __html: body }} />
                <button onClick={handleEditEvent} className="GH_Btn edit"><i className="bi bi-pencil"></i></button>
            </div>
        }
        {editActive && 
         <div style={{ position: 'relative', maxWidth: '1000px'}} className="edit-box">
            {documentEdit ? 
                <>
                    <button onClick={handleSaveChanges} style={{position:'absolute', right: '10px'}} className="GH_Btn save custom-editor-button"><i className="bi bi-floppy"></i></button>
                    <TextEditor body={editedText} onTextChange={handleDocBodyChange}/>
                </>
            :
                <input onMouseOut={handleSaveChanges} type="text" value={editedText} onChange={handleTextChange} style={{ width: '100%' , overflowY:'scroll'}}/>   
            }
        </div>}
    </>
    )
}

export default EditableProductDetailsItem