import React, { useState } from 'react';
import './CatalogRequestForm.css';
import { PhysicalProductRequestDTO } from '../../Controller/DTO/request-dto/PhysicalProductRequestDTO';

// Assuming the PhysicalProductRequestDTO interface has been updated to include images and categories
interface EnhancedPhysicalProductRequestDTO extends PhysicalProductRequestDTO {
  images?: File[];
}

const CatalogRequestForm: React.FC = () => {
  const [physicalProductRequest, setPhysicalProductRequest] = useState<EnhancedPhysicalProductRequestDTO>({
    dateReceived: '',
    status: 'pending',
    requestType: 'create',
    title: '',
    price: 0,
    description: '',
    postDate: '',
    count: 0,
    sellerId: 0,
    category: ''
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setPhysicalProductRequest({ ...physicalProductRequest, [name]: value });
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setPhysicalProductRequest({ ...physicalProductRequest, images: Array.from(e.target.files) });
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Submit form logic here
    console.log(physicalProductRequest);
  };

  return (
    <form onSubmit={handleSubmit} className='form-container'>
        <h2 className='form-title'>Catalog Request Form</h2>
        <label className='form-label'>
            1. Add title to your product
            <input type="text" name="title" className='form-input' value={physicalProductRequest.title} onChange={handleInputChange} />
        </label>
        <label className='form-label'>
            2. Add description to your product
            <textarea name="description" className='form-textarea' value={physicalProductRequest.description} onChange={handleInputChange} />
        </label>
        <label className='form-label'>
            3. Do you have any images related to your product?
            <input type="file" name="images" className='form-input' multiple onChange={handleFileChange} />
        </label>
        <label className='form-label'>
            4. Choose Product Type
            <select name="requestType" className='form-select' value={physicalProductRequest.requestType} onChange={handleInputChange}>
            <option value="option1">Option 1</option>
            <option value="option2">Option 2</option>
            </select>
        </label>
        <label className='form-label'>
            5. Choose product category
            <select name="category" className='form-select' value={physicalProductRequest.category} onChange={handleInputChange}>
            <option value="">Select a category</option>
            <option value="category1">Category 1</option>
            <option value="category2">Category 2</option>
            </select>
        </label>
        <label className='form-label'>
            6. How many products are available?
            <input type="number" name="count" className='form-input' value={physicalProductRequest.count} onChange={handleInputChange} />
        </label>
        <label className='form-label'>
            7. Enter Price
            <input type="number" name="price" className='form-input' value={physicalProductRequest.price} onChange={handleInputChange} />
        </label>
        <button type="submit" className="form-button">Submit</button>
    </form>
  );
};

export default CatalogRequestForm;
