import React, { useState } from 'react';
import { getId, getName, getEmail, getPhone, getAddress, getBalance, getNationalID, getDateJoined,getSellerDescription, getVatRegistrationNumber } from '../../CurrentSession';
import { httpRequest } from '../../Controller/HttpProxy';

interface SellerProfile {
  id: number;
  name: string;
  email: string;
  phone: string;
  address: string;
  balance: number;
  nationalID: string;
  dateJoined: Date;
  sellerDescription: string;
  vatRegistrationNumber: string;
}

const updateSessionStorage = (updatedFields) => {
  Object.keys(updatedFields).forEach(key => {
    const value = updatedFields[key];
    if (value !== undefined && value !== null) {
      window.sessionStorage.setItem(key, value.toString());
    }
  });
};

const SellerProfileDetails: React.FC<{ profile: SellerProfile, onEdit: (field: keyof SellerProfile) => void }> = ({ profile, onEdit }) => {
    return (
      <div style={styles.details}>
        <h2 style={styles.sectionTitle}>Profile Details</h2>
  
        <div style={styles.detailItem}>
          <p><strong>ID:</strong> {profile.id}</p>
        </div>
  
        <div style={styles.detailItem}>
          <span><strong>Name:</strong> {profile.name}</span>
          <button style={styles.editButton} onClick={() => onEdit('name')}>Edit</button>
        </div>
  
        <div style={styles.detailItem}>
          <span><strong>Email:</strong> {profile.email}</span>
          <button style={styles.editButton} onClick={() => onEdit('email')}>Edit</button>
        </div>
  
        <div style={styles.detailItem}>
          <span><strong>Phone:</strong> {profile.phone}</span>
          <button style={styles.editButton} onClick={() => onEdit('phone')}>Edit</button>
        </div>
  
        <div style={styles.detailItem}>
          <span><strong>Address:</strong> {profile.address}</span>
          <button style={styles.editButton} onClick={() => onEdit('address')}>Edit</button>
        </div>
  
        <div style={styles.detailItem}>
          <span><strong>Seller Description:</strong> {profile.sellerDescription}</span>
          <button style={styles.editButton} onClick={() => onEdit('sellerDescription')}>Edit</button>
        </div>
  
        {/* Display non-editable fields without edit buttons */}
        <div style={styles.detailItem}>
          <p><strong>National ID:</strong> {profile.nationalID}</p>
        </div>
        
        <div style={styles.detailItem}>
        <p><strong>Date Joined:</strong> {profile.dateJoined.toLocaleDateString()}</p>

        </div>
  
        <div style={styles.detailItem}>
          <p><strong>VAT Registration Number:</strong> {profile.vatRegistrationNumber}</p>
        </div>
  
      </div>
    );
  };
  
const SellerProfilePage: React.FC = () => {
  const [sellerProfile, setSellerProfile] = useState<SellerProfile>({
    id: getId(),
    name: getName(),
    email: getEmail(),
    phone: getPhone(),
    address: getAddress(),
    balance: getBalance(),
    nationalID: getNationalID(),
    dateJoined: new Date(getDateJoined()),
    sellerDescription: getSellerDescription(),
    vatRegistrationNumber: getVatRegistrationNumber(),
  });

  const [editField, setEditField] = useState<keyof SellerProfile | null>(null);
  const [editValue, setEditValue] = useState('');

  const handleEdit = (field: keyof SellerProfile) => {
    if (['name', 'email', 'phone', 'address', 'sellerDescription'].includes(field)) {
      setEditField(field);
      setEditValue(sellerProfile[field] as unknown as string);
    }
  };

  type UpdateSellerDTO = {
    id: number;
    email: string;
    name: string;
    phone: string;
    address: string;
    balance: number;
    nationalID: string;
    dateJoined: Date;
    sellerDescription: string;
    vatRegistrationNumber: string;
  };

  const handleSave = () => {
    if (editField) {
      const updatedProfile: UpdateSellerDTO = {
        ...sellerProfile,
        [editField]: editValue
      };
      console.log(updatedProfile)
      httpRequest('post', '/profile_update/seller', updatedProfile)
        .then(response => {
          alert(response.data);
          updateSessionStorage(updatedProfile);
        })
        .catch(error => {
          console.error('Error:', error);
        });

      setSellerProfile({
        ...sellerProfile,
        [editField]: editValue,
      });

      setEditField(null);
    }
  };

  return (
    <div style={styles.page}>
      <h1 style={styles.title}>Seller Profile</h1>
      <SellerProfileDetails profile={sellerProfile} onEdit={handleEdit} />

      {editField && (
        <div style={styles.popup}>
          <input style={styles.input} type="text" value={editValue} onChange={(e) => setEditValue(e.target.value)} />
          <button style={styles.saveButton} onClick={handleSave}>Save</button>
        </div>
      )}
    </div>
  );
};

const styles = {
    page: {
        display: 'flex' as 'flex',
        flexDirection: 'column' as 'column',
        alignItems: 'center',
        justifyContent: 'flex-start', // Adjust this for vertical alignment
        height: '100vh',
        backgroundColor: '#1a1a1a',
        color: 'white',
        fontFamily: 'Arial, sans-serif',
        padding: '50px', // Reduced top padding
      },
  title: {
    color: '#4CAF50',
    alignSelf: 'center',
    textAlign: 'center' as 'center',
    width: '100%',
    fontSize: '36px', // Increase font size for the title
    marginBottom: '30px', // Increase margin bottom to give more space below the title
  },
  details: {
    backgroundColor: '#333',
    borderRadius: '50px',
    
    padding: '30px', // Increase padding inside the details box
    width: '100%',
    maxWidth: '800px', // Increase maxWidth to make the box wider
    marginTop: '20px',
  },
  
  sectionTitle: {
    color: '#e91e63',
    fontSize: '30px', // Increase font size for section titles
  },
  detailItem: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    fontSize: '18px', // Increase font size for detail items
    marginBottom: '15px', // Increase spacing between detail items
    // If you have a specific width in mind for your container, set it here, 
    // or else it will expand to the width of its parent container.
    width: '100%',
  },
  editButton: {
    backgroundColor: '#4CAF50',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
    marginLeft: '10px', // Add space between text and button
    padding: '10px 15px', // Increase padding inside the edit buttons
    fontSize: '16px', // Increase font size for button text
  },
  popup: {
    position: 'fixed' as 'fixed',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    backgroundColor: 'white',
    padding: '30px', // Increase padding inside the popup
    borderRadius: '10px',
    display: 'flex',
    flexDirection: 'column' as 'column',
    alignItems: 'center',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',
    zIndex: 1000,
  },
  input: {
    margin: '10px 0',
    fontSize: '18px', // Increase font size for inputs
    padding: '12px', // Increase padding for inputs
    width: '200px',
  },
  saveButton: {
    padding: '12px 20px', // Increase padding inside the save button
    fontSize: '16px', // Increase font size for button text
    backgroundColor: '#4CAF50',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
  },
  creditCardForm: {
    position: 'fixed' as 'fixed',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    backgroundColor: 'white',
    padding: '30px', // Increase padding inside the popup
    borderRadius: '10px',
    display: 'flex',
    flexDirection: 'column' as 'column',
    alignItems: 'center',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',
    zIndex: 1000,
  },
};

export default SellerProfilePage;
