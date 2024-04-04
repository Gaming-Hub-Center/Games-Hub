import React, { useState } from 'react';
import { getId, getName, getEmail, getPhone, getAddress, getBalance } from '../../session/CurrentSession';
import { httpRequest } from '../../Controller/HttpProxy';
import { NavbarC } from '../../Components/NavbarC';


interface BuyerProfile {
  id: number;
  name: string;
  email: string;
  phone: string;
  address: string;
  balance: number;
}

const updateSessionStorage = (updatedFields) => {
  // Loop through each field in the updatedFields object
  Object.keys(updatedFields).forEach(key => {
    const value = updatedFields[key];
    // Check if the value is not undefined or null, then update session storage
    if (value !== undefined && value !== null) {
      window.sessionStorage.setItem(key, value.toString());
    }
  });
};


const BuyerProfileDetails: React.FC<{ profile: BuyerProfile, onEdit: (field: keyof BuyerProfile) => void }> = ({ profile, onEdit }) => {
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
        <span><strong>Balance:</strong> ${profile.balance}</span>
        <button style={styles.editButton} onClick={() => onEdit('balance')}>Add</button>
      </div>
    </div>
  );
};

const CreditCardForm: React.FC<{ onSave: () => void, onChange: (e: React.ChangeEvent<HTMLInputElement>) => void }> = ({ onSave, onChange }) => {
  return (
    <div style={styles.creditCardForm}>
      <input style={styles.input} type="text" placeholder="Card Number" onChange={onChange} name="cardNumber" />
      <input style={styles.input} type="text" placeholder="Expiry Date" onChange={onChange} name="expiryDate" />
      <input style={styles.input} type="text" placeholder="CVV" onChange={onChange} name="cvv" />
      <button style={styles.saveButton} onClick={onSave}>Save</button>
    </div>
  );
};


const BuyerProfilePage: React.FC = () => {
  const [buyerProfile, setBuyerProfile] = useState<BuyerProfile>({
    id: getId(),
    name: getName(),
    email: getEmail(),
    phone: getPhone(),
    address: getAddress(),
    balance: getBalance()
  });

  const [editField, setEditField] = useState<keyof BuyerProfile | null>(null);
  const [editValue, setEditValue] = useState('');
  const [showCreditCardForm, setShowCreditCardForm] = useState(false);
  const [creditCardInfo, setCreditCardInfo] = useState({ cardNumber: '', expiryDate: '', cvv: '' });



  const handleEdit = (field: keyof BuyerProfile) => {
    if (field === 'balance') {
      setShowCreditCardForm(true);
    } else {
      setEditField(field);
      setEditValue(buyerProfile[field] as unknown as string);
    }
  };
  const handleCreditCardSave = () => {
    setShowCreditCardForm(false);
    setEditField('balance');
    setEditValue(buyerProfile.balance.toString());

  };





  type UpdateBuyerDTO = {
    id: number;
    email: string
    name: string;
    phone: string;
    address: string;
    balance: number;
  };

  const handleSave = () => {
    if (editField) {
      const updatedProfile: UpdateBuyerDTO = {
        id: buyerProfile.id,
        email: buyerProfile.email,
        name: buyerProfile.name,
        phone: buyerProfile.phone,
        address: buyerProfile.address,
        balance: buyerProfile.balance
      };



      let updatedValue: string | number = editValue; // Initialize as string type by default
      let writeValue: any = editValue
      if (editField === 'balance') {
        updatedValue = parseFloat(editValue) + buyerProfile.balance
        writeValue = updatedValue
      }

      updatedProfile[editField as string] = updatedValue;


      console.log(updatedProfile)
      httpRequest('post','/profile_update/buyer',updatedProfile)
        .then(response => {
          alert(response.data)
          updateSessionStorage(updatedProfile);
        })
        .catch(error => {
          console.error('Error:', error);
        });
      setBuyerProfile({
        ...buyerProfile,
        [editField]: writeValue,
      });
      setEditField(null);
    }
  };

  return (
    <div style={styles.page}>
      <NavbarC productType="your_product_type" updateProductCardPropsList={() => {}} />
      <h1 style={styles.title}>Buyer Profile</h1>
      <BuyerProfileDetails profile={buyerProfile} onEdit={handleEdit} />

      {editField && (
        <div style={styles.popup}>
          <input style={styles.input} type="text" value={editValue} onChange={(e) => setEditValue(e.target.value)} />
          <button style={styles.saveButton} onClick={handleSave}>Save</button>
        </div>
      )}

      {showCreditCardForm && (
        <CreditCardForm onSave={handleCreditCardSave} onChange={(e) => setCreditCardInfo({ ...creditCardInfo, [e.target.name]: e.target.value })} />
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
    backgroundColor: 'black',
    color: 'white',
    fontFamily: 'Arial, sans-serif',
    padding: '0px', // Reduced top padding
  },
  title: {
    color: '#733BC0',
    alignSelf: 'center',
    textAlign: 'center' as 'center',
    width: '100%',
    fontSize: '36px', // Increase font size for the title
    marginBottom: '30px', // Increase margin bottom to give more space below the title
    padding: '10px', // Reduced top padding
  },
  details: {
    backgroundColor: '#333',
    borderRadius: '50px',

    padding: '30px', // Increase padding inside the details box
    width: '100%',
    maxWidth: '800px', // Increase maxWidth to make the box wider
    marginTop: '10px',
  },

  sectionTitle: {
    color: '#733BC0',
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
    backgroundColor: '#733BC0',
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
    backgroundColor: '#733BC0',
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

export default BuyerProfilePage;
