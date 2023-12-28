import React, { useEffect, useState } from 'react';
import './CatalogRequestForm.css';
import { PhysicalProductRequestDTO } from '../../Controller/DTO/request-dto/PhysicalProductRequestDTO';
import { httpRequest } from '../../Controller/HttpProxy';
import { getId } from '../../CurrentSession';
import AlertOk from '../../Components/AlertDisnissible';
import AlertError from '../../Components/AlertError';
import { DigitalProductRequestDTO } from '../../Controller/DTO/request-dto/DigitalProductRequestDTO';
import AlertAleadyExists from '../../Components/AlertAleadyExists';
import axios from 'axios';
import { Input, Button, Stack,  } from "@mui/material";
import { FaStore } from 'react-icons/fa';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

const CatalogRequestForm: React.FC = () => {
  const [physicalProductRequest, setPhysicalProductRequest] = useState<PhysicalProductRequestDTO>({
    dateReceived: new Date().toISOString().split('T')[0],
    status: 'Pending',
    requestType: 'create',
    title: '',
    price: 0,
    description: '',
    postDate: '',
    count: 0,
    sellerId: getId(),
    category: 'Physical Category 1',
    images: String['']
  });

  const [digitalProductRequest, setDigitalProductRequest] = useState<DigitalProductRequestDTO>({ 
    dateReceived: new Date().toISOString().split('T')[0],
    status: 'Pending',
    requestType: 'create',
    title: '',
    price: 0,
    description: '',
    postDate: '',
    count: 0,
    sellerId: getId(),
    category: 'Digital Category 1',
    code: '',
    images: String['']
  });

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

  const [showSuccessAlert, setShowSuccessAlert] = useState(false);
  const [showErrorAlert, setShowErrorAlert] = useState(false);
  const [showAlertAleadyExists, setAlertAleadyExists] = useState(false);
  const [errors, setErrors] = useState({ title: '', description: '', count: '', price: '' });
  const [categories, setCategories] = useState([]);
  const [productType, setProductType] = useState<'physical' | 'digital'>('physical');

  const [tempImageUrl, setTempImageUrl] = useState('');
  const [imageFile, setImageFile] = useState(null)
  const [uploadedFileName, setUploadedFileName] = useState(null);
  const [isUploading, setIsUploading] = useState(false);


  const validate = () => {
    let tempErrors = { title: '', description: '', count: '', price: '', images: '' };
    let isValid = true;
  
    // Check for empty title
    if ((!physicalProductRequest.title && productType === 'physical') || (!digitalProductRequest.title && productType === 'digital')) {
      tempErrors.title = "Title cannot be empty";
      isValid = false;
    }
    // Check for empty description
    if ((!physicalProductRequest.description && productType === 'physical') || (!digitalProductRequest.description && productType === 'digital')) {
      tempErrors.description = "Description cannot be empty";
      isValid = false;
    }
    // Check for positive count
    if ((physicalProductRequest.count <= 0 && productType === 'physical') || (digitalProductRequest.count <= 0 && productType === 'digital')) {
      tempErrors.count = "Count must be a positive number";
      isValid = false;
    }
    // Check for positive price
    if ((physicalProductRequest.price <= 0 && productType === 'physical') || (digitalProductRequest.price <= 0 && productType === 'digital')) {
      tempErrors.price = "Price must be a positive number";
      isValid = false;
    }
  
    setErrors(tempErrors);
    return isValid;
  };

  useEffect(() => {
    console.log("Updated product type: ", productType); // This will log the current product type
  
    if (productType === 'physical') {
      setCategories([
        'Consoles',
        'Accessories',
        'Merchandise',
        'Gaming Furniture',
        'Physical Games',
        'PC Hardware',
        'Virtual Reality (VR)'
      ]);
    } else if (productType === 'digital') {
      setCategories([
        'Digital Games',
        'Game Subscriptions',
        'Software and Utilities',
        'Mobile Gaming',
        'E-Books and Guides',
        'Online Tournaments and Events'
      ]);
    }
    
  }, [productType]);
  
  const handleProductTypeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedProductType = e.target.value; 
    console.log("Selected product type: ", selectedProductType); 
    setProductType(selectedProductType as 'physical' | 'digital');
  };  

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    
    if (name === "count" || name === "price") {
        const numericValue = parseFloat(value);
        if (numericValue < 0) return;
    }

    console.log(digitalProductRequest.description)

    if (productType === 'physical') {
        setPhysicalProductRequest(prevState => ({
            ...prevState,
            [name]: value
        }));
    } else if (productType === 'digital') {
        setDigitalProductRequest(prevState => ({
            ...prevState,
            [name]: value
        }));
        console.log(digitalProductRequest)
    }
};

const clearForm = () => {
  physicalProductRequest.category = 'Physical Category 1'
  physicalProductRequest.count = 0
  physicalProductRequest.description = ''
  physicalProductRequest.price = 0
  physicalProductRequest.title = ''
  
  digitalProductRequest.category = 'Digital Category 1'
  digitalProductRequest.count = 0
  digitalProductRequest.price = 0
  digitalProductRequest.description = ''
  digitalProductRequest.title = ''

  setUploadedFileName('');
  setPhysicalProductRequest(physicalProductRequest)
  setDigitalProductRequest(digitalProductRequest)
};

const uploadImage = async () => {
  if (!(imageFile instanceof File)) {
    console.error("The imageFile is not an instance of File.");
    return;
  }

  setIsUploading(true); 
  
  const formData = new FormData();
  formData.append("file", imageFile);
  formData.append("upload_preset", "ml_default");
  formData.append("api_key", "941432731188379");

  // Log the file data
  for (let [key, value] of formData.entries()) {
    console.log(key, value);
  }

  try {
    const uploadResponse = await axios.post(
      "https://api.cloudinary.com/v1_1/dvnf3jmrz/image/upload",
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }
    );
    setIsUploading(false); // Stop loading on success
    console.log(uploadResponse.data.secure_url + " before..")
    return uploadResponse.data;
  } catch (error) {
    if (error.response) {
      console.error(error.response.data);
      console.error(error.response.status);
      console.error(error.response.headers);
    } else if (error.request) {
      console.error(error.request);
    } else {
      console.error('Error', error.message);
    }
    throw error;
  }
};


const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  if (e.target.files && e.target.files[0]) {
    const fileName = e.target.files[0].name;
    setUploadedFileName(fileName);
    console.log("Selected file:", e.target.files[0]);
    setTempImageUrl(URL.createObjectURL(e.target.files[0]));
    setImageFile(e.target.files[0]);
  } else {
    console.error("No file selected");
  }
};

const handleSubmit = async (e: React.FormEvent) => {
  e.preventDefault();
  setShowSuccessAlert(false);
  setShowErrorAlert(false);
  setAlertAleadyExists(false);

  const isValid = validate();
  if (!isValid) return;

  if (imageFile) {
    try {
      const uploadResult = await uploadImage();
      const url = uploadResult.secure_url
      console.log(uploadResult)
      
      const requestPayload = productType === 'physical' 
        ? { ...physicalProductRequest, images: [url] }
        : { ...digitalProductRequest, images: [url] }; 
      
      // Proceed to submit the request payload
      await submitRequestPayload(requestPayload);
    } catch (error) {
      // Handle image upload error
      console.error("Error uploading image: ", error);
      setShowErrorAlert(true);
      return;
    }
  } else {
    // Proceed without image upload
    const requestPayload = productType === 'physical' 
      ? physicalProductRequest 
      : digitalProductRequest;
    await submitRequestPayload(requestPayload);
  }
};

const submitRequestPayload = async (requestPayload) => {
  const endpoint = `/seller/request/create/${productType}`;
  httpRequest("POST", endpoint, requestPayload)
    .then((response) => {
      console.log(response);
      setShowSuccessAlert(true);
      clearForm();
    })
    .catch((error) => {
      console.log(error.response?.status);
      if (error.response && error.response.status === 406) {
        setAlertAleadyExists(true);
      } else {
        setShowErrorAlert(true);
      }
    });
};


  return (
    <div>
    {showSuccessAlert && (
      <div>
        <AlertOk />
      </div>
    )}

    {showErrorAlert && (
      <div>
        <AlertError />
      </div>
    )}

    {showAlertAleadyExists && (
      <div>
        <AlertAleadyExists />
      </div>
    )}

    <form onSubmit={handleSubmit} className='form-container'>
        <h2 className='form-title'>Catalog Request Form</h2>
        <label className='form-label'>
            1. Add title to your product
            <input 
              type="text" 
              name="title" 
              className='form-input' 
              value={productType === 'physical' ? physicalProductRequest.title : digitalProductRequest.title}
              onChange={handleInputChange} 
            />
        </label>
        <div className='error-message'>{errors.title}</div>
        <label className='form-label'>
            2. Add description to your product
            <textarea 
              name="description" 
              className='form-textarea' 
              value={productType === 'physical' ? physicalProductRequest.description : digitalProductRequest.description}
              onChange={handleInputChange} 
            />
        </label>
        <div className='error-message'>{errors.description}</div>
        <label className='form-label'>
          3. Do you have any images related to your product?
          <Button component="label" sx={{ margin: '1vh' }} variant="contained" startIcon={<CloudUploadIcon />}>
            Upload Image
            <Input
                sx={VisuallyHiddenStyle}
                type="file"
                onChange={handleFileChange}
                inputProps={{ multiple: true }} 
            />
          </Button>
          {uploadedFileName && (
            <div>
                {uploadedFileName}
            </div>
          )}
        </label>
        <label className='form-label'>
            4. Choose Product Type
            <select 
              name="productType" 
              className='form-select' 
              value={productType} 
              onChange={handleProductTypeChange}
            >
            <option value="physical">Physical</option>
            <option value="digital">Digital</option>
            </select>
        </label>
        <label className='form-label'>
          5. Choose product category
          <select
            name="category"
            className='form-select'
            value={productType === 'physical' ? physicalProductRequest.category : digitalProductRequest.category}
            onChange={handleInputChange}
          >
            {categories.map((category, index) => (
                <option key={index} value={category}>{category}</option>
            ))}
          </select>

        </label>
        <label className='form-label'>
            6. How many products are available?
            <input 
              type="number" 
              name="count" 
              className='form-input' 
              value={productType === 'physical' ? physicalProductRequest.count : digitalProductRequest.count}
              onChange={handleInputChange} 
              min="0"
            />
        </label>
        <div className='error-message'>{errors.count}</div>
        <label className='form-label'>
            7. Enter Price
            <input 
              type="number" 
              name="price" 
              className='form-input' 
              value={productType === 'physical' ? physicalProductRequest.price : digitalProductRequest.price}
              onChange={handleInputChange} 
              min="0"
            />
        </label>
        <div className='error-message'>{errors.price}</div>
        <button type="submit" className="form-button" disabled={isUploading}>
          Submit
        </button>
        {isUploading && (
            <div className="loading-indicator">
              Processing, please wait...
            </div>
          )}
      </form>
    </div>
  );
};

export default CatalogRequestForm;