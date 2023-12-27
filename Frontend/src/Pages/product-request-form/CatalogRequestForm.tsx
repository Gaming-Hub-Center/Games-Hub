import React, { useEffect, useState } from 'react';
import './CatalogRequestForm.css';
import { PhysicalProductRequestDTO } from '../../Controller/DTO/request-dto/PhysicalProductRequestDTO';
import { httpRequest } from '../../Controller/HttpProxy';
import { getId } from '../../CurrentSession';
import AlertOk from '../../Components/AlertDisnissible';
import AlertError from '../../Components/AlertError';
import { DigitalProductRequestDTO } from '../../Controller/DTO/request-dto/DigitalProductRequestDTO';
import AlertAleadyExists from '../../Components/AlertAleadyExists';
import { cld } from '../../Utilities/cloudinaryConfig';
import axios from 'axios';

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

  const [showSuccessAlert, setShowSuccessAlert] = useState(false);
  const [showErrorAlert, setShowErrorAlert] = useState(false);
  const [showAlertAleadyExists, setAlertAleadyExists] = useState(false);
  const [errors, setErrors] = useState({ title: '', description: '', count: '', price: '' });
  const [categories, setCategories] = useState([]);
  const [productType, setProductType] = useState<'physical' | 'digital'>('physical');
  const [image, setSelectedImage] = useState('');

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
      setCategories(['Physical Category 1', 'Physical Category 2']);
    } else if (productType === 'digital') {
      setCategories(['Digital Category 1', 'Digital Category 2']);
    }
  }, [productType]);
  
  const handleProductTypeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedProductType = e.target.value; // 'physical' or 'digital'
    console.log("Selected product type: ", selectedProductType); // Add this line to check the selected value
    setProductType(selectedProductType as 'physical' | 'digital');
  };  

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    
    // Prevent negative values for 'count' and 'price'
    if (name === "count" || name === "price") {
        const numericValue = parseFloat(value);
        if (numericValue < 0) return; // Don't update the state if the value is negative
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

// React component pseudo-code
const getSignature = async () => {
  const response = await axios.get('/api/cloudinary_signature');
  return response.data.signature;
};

  const uploadImage = async (file: File) => {
    const formData = new FormData();
    const timestamp = Math.round((new Date()).getTime() / 1000);
    const signature = await getSignature();

    formData.append('file', file);
    formData.append('timestamp', timestamp.toString());
    formData.append('api_key', '712766868742575');
    formData.append('signature', signature);
    // Include additional upload parameters, such as 'upload_preset' if needed

    const uploadResponse = await axios.post(`https://api.cloudinary.com/v1_1/diqxjlzau/image/upload`, formData);
    return uploadResponse.data; // Contains the URL of the uploaded image
  };


  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) return;

    let imageUrls = [];
    for (let i = 0; i < e.target.files.length; i++) {
        const file = e.target.files[i];
        const url = await uploadImage(file);
        if (url) {
            imageUrls.push(url);
        }
    }

    if (productType === 'physical') {
        setPhysicalProductRequest(prev => {
            // Ensure prev.images is an array
            const prevImagesArray = Array.isArray(prev.images) ? prev.images : [];
            return {
                ...prev,
                images: [...prevImagesArray, ...imageUrls]
            };
        });
    } else {
        setDigitalProductRequest(prev => {
            // Ensure prev.images is an array
            const prevImagesArray = Array.isArray(prev.images) ? prev.images : [];
            return {
                ...prev,
                images: [...prevImagesArray, ...imageUrls]
            };
        });
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
  
    setPhysicalProductRequest(physicalProductRequest)
    setDigitalProductRequest(digitalProductRequest)
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setShowSuccessAlert(false);
    setShowErrorAlert(false);
    setAlertAleadyExists(false);
    
    const isValid = validate(); // Make sure validate function is updated to validate based on productType
    if (!isValid) return;
  
    
    const requestPayload = productType === 'physical' ? physicalProductRequest : digitalProductRequest;  
  
    httpRequest("POST", `/product-request/create`, requestPayload)
      .then((response) => {
        console.log(response);
        setShowSuccessAlert(true);
        clearForm();
      })
      .catch((error) => {
        console.log(error.response.status);
        if(error.response.status === 406) {
            setAlertAleadyExists(true)
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
              <input 
                  type="file" 
                  name="images" 
                  className='form-input' 
                  multiple 
                  onChange={handleFileChange} 
              />
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
          <button type="submit" className="form-button">Submit</button>
      </form>
    </div>
  );
};

export default CatalogRequestForm;