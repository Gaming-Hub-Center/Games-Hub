import React, { useEffect, useState } from 'react';
import './CatalogRequestForm.css';
import { PhysicalProductRequestDTO } from '../../Controller/DTO/request-dto/PhysicalProductRequestDTO';
import { httpRequest } from '../../Controller/HttpProxy';
import { getId } from '../../CurrentSession';
import AlertOk from '../../Components/AlertDisnissible';
import AlertError from '../../Components/AlertError';
import { DigitalProductRequestDTO } from '../../Controller/DTO/request-dto/DigitalProductRequestDTO';

const CatalogRequestForm: React.FC = () => {
  const [physicalProductRequest, setPhysicalProductRequest] = useState<PhysicalProductRequestDTO>({
    dateReceived: new Date().toISOString().split('T')[0],
    status: 'pending',
    requestType: 'create',
    title: '',
    price: 0,
    description: '',
    postDate: '',
    count: 0,
    sellerId: getId(),
    category: ''
  });

  const [digitalProductRequest, setDigitalProductRequest] = useState<DigitalProductRequestDTO>({ 
    dateReceived: new Date().toISOString().split('T')[0],
    status: 'pending',
    requestType: 'create',
    title: '',
    price: 0,
    description: '',
    postDate: '',
    count: 0,
    sellerId: getId(),
    category: '',
    code: ''
  });

  const [showSuccessAlert, setShowSuccessAlert] = useState(false);
  const [showErrorAlert, setShowErrorAlert] = useState(false);
  const [errors, setErrors] = useState({ title: '', description: '', count: '', price: '' });
  const [categories, setCategories] = useState([]);
  const [productType, setProductType] = useState<'physical' | 'digital'>('physical');

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
    }
};


  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    // if (e.target.files) {
    //   setPhysicalProductRequest({ ...physicalProductRequest, images: Array.from(e.target.files) });
    // }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setShowSuccessAlert(false);
    setShowErrorAlert(false);
    
    const isValid = validate(); // Make sure validate function is updated to validate based on productType
    if (!isValid) return;
  
    const requestPayload = productType === 'physical' ? physicalProductRequest : digitalProductRequest;  
  
    console.log(requestPayload)
    console.log(productType)
    httpRequest("POST", `/product-request/create/${productType}`, requestPayload)
      .then((response) => {
        console.log(response);
        setShowSuccessAlert(true);
      })
      .catch((error) => {
        console.log(error);
        setShowErrorAlert(true);
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
                multiple onChange={handleFileChange} 
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
                min="0" // HTML5 attribute to prevent negative numbers
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
                min="0" // HTML5 attribute to prevent negative numbers
              />
          </label>
          <div className='error-message'>{errors.price}</div>
          <button type="submit" className="form-button">Submit</button>
      </form>
    </div>
  );
};

export default CatalogRequestForm;