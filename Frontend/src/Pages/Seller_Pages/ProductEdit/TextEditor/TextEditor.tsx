import { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';


interface TextEditorProps {
  body: string;
  onTextChange: (newText: string) => void; 
}

function TextEditor({body, onTextChange}: TextEditorProps){

  return (
    <>
      <ReactQuill
        theme="snow"
        value={body}
        onChange={onTextChange}
        modules={{
          toolbar: [
            ['bold', 'italic', 'underline', 'strike'],
            [{ 'size': ['small', 'medium', 'large', 'huge'] }],
            [{ 'list': 'ordered' }, { 'list': 'bullet' }],
          ],
        }}
      />
      {/* <div>
        <strong>Preview:</strong>
        <div dangerouslySetInnerHTML={{ __html: text }} />
      </div> */}
    </>
  );
};

export default TextEditor;
