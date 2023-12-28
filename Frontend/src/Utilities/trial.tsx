
import { IconButton, Typography, Paper, Box, Stack, Avatar, Modal, Input, Button, Backdrop, Fade } from "@mui/material";
import { useState, useEffect } from "react";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import axios from "axios";
import * as React from "react";

export default function Profile () {
    const modalStyle = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        borderRadius: '2%',
        boxShadow: 24,
        p: 4,
    }
    const VisuallyHiddenStyle ={
        clip: 'rect(0 0 0 0)',
        clipPath: 'inset(50%)',
        height: 1,
        overflow: 'hidden',
        position: 'absolute',
        bottom: 0,
        left: 0,
        whiteSpace: 'nowrap',
        width: 1,
    }
    const user = {
        firstName: "name",
        lastName: "lname",
        phone: "0123456789",
        dateOfBirth:"1960-01-01",
        imageUrl:"",
        role: "Staff"
    }

    const [firstName, setFirstName] = useState(user.firstName);
    const [lastName, setLastName] = useState(user.lastName);
    const [phone, setPhone] = useState(user.phone);
    const [dateOfBirth, setDob] = useState(user.dateOfBirth);
    const [imageUrl, setImageUrl] = useState(user.imageUrl);
    const role = user.role;

    const [modal, setModal] = useState(false)
    const [tempImageUrl, setTempImageUrl] = useState('');
    const [imageFile, setImageFile] = useState(null)

    useEffect(() => {
        user.firstName = firstName
        user.lastName = lastName
        user.phone = phone
        user.imageUrl = imageUrl
        localStorage.setItem("user", JSON.stringify(user));
        //axios.post(`${globals.baseURL}/person/test`, globals.user)       modify when routine is added in backend
    })

    const chooseImage = (e) => {
        setTempImageUrl(URL.createObjectURL(e.target.files[0]));
        setImageFile(e.target.files[0]);
    }

    const uploadImage = async (event) => {
        if(imageFile == null)
            return
        let uploadedImage = {};
        try {
            const formData = new FormData ();
            formData.append("file", imageFile);
            formData.append("upload_preset", "lspohdlx");
            formData.append("api_key", "465175384975426");

            const response = await axios.post(
                "https://api.cloudinary.com/v1_1/dgqdribpo/image/upload",
                formData
            );
            uploadedImage = response.data.secure_url;
            setImageUrl(uploadImage as any);

            closeHandler()
        } catch (error) {
            console.log(error)
        }
    }

    const openHandler = () => {
        setModal(true)
        setTempImageUrl(imageUrl)
    }

    const closeHandler = () => {
        setModal(false)
    }

    return (
        <>
            <Modal
                open={modal}
                onClose={closeHandler}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
                closeAfterTransition
                slots={{ backdrop: Backdrop }}
                slotProps={{
                    backdrop: {
                        timeout: 250,
                    },
                }}
            >
                <Fade in={modal}>
                    <Box sx={modalStyle}>
                        <Stack alignItems={'center'}>
                            <Typography id="modal-modal-title" variant="h5" component="h2">
                                Upload Your Profile Image
                            </Typography>
                            <Avatar
                                alt={firstName.toUpperCase()}
                                src={tempImageUrl}
                                sx={{ width: '15vw', height: '15vw', margin: '2vh' }}
                            />
                        </Stack>
                        <Stack>
                            <Button component="label" sx={{
                                margin:'1vh'
                            }} variant="contained" startIcon={<CloudUploadIcon />}>
                                Upload Image
                                <Input sx={VisuallyHiddenStyle} type="file" onChange={chooseImage}/>
                            </Button>
                            <Button variant="outlined" sx={{
                                margin:'1vh'
                            }}
                                    onClick={uploadImage}>Confirm</Button>
                        </Stack>
                    </Box>
                </Fade>
            </Modal>
        </>
    );
}