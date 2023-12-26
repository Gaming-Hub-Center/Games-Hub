import { useState } from 'react';
import { FaCheckCircle, FaFileAlt, FaHourglassHalf, FaHourglassStart, FaStore, FaTimesCircle, FaUser } from 'react-icons/fa';
import './AdminDashboard.css';
import NavBarAdmin from '../../Components/admin/NavBarAdmin';
import PendingProductsComponent from '../../Components/admin/PendingProducts';

// Define a type for the valid section keys
type Section = 'Pending Physical Products' | 'Pending Digital Products' | 'Approved Products' | 'Declined Products' | 'Buyers' | 'Sellers';

// Define a type for the sections data with the above keys
type SectionsData = {
  [key in Section]: JSX.Element;
};

// Define your sectionsData with the corresponding type
const sectionsData: SectionsData = {
    "Pending Physical Products": <PendingProductsComponent productType="physical" />,
    "Pending Digital Products":<PendingProductsComponent productType="digital" />,
    "Approved Products": <p></p>,
    "Declined Products": <p></p>,
    Buyers: <p></p>,
    Sellers: <p></p>
};

const HomePageAdmin = () => {
  // Use the Section type for the activeSection state
  const [activeSection, setActiveSection] = useState<Section>('Pending Physical Products');

  return (
    <div className="admin-homepage">
      <NavBarAdmin />

      <div className="admin-main-content">
        <aside className="admin-sidebar">
            <ul>
                <li onClick={() => setActiveSection('Pending Physical Products')}>
                    <FaHourglassStart className="admin-icon" /> Pending Physical Products
                </li>
                <li onClick={() => setActiveSection('Pending Digital Products')}>
                    <FaHourglassStart className="admin-icon" /> Pending Digital Products
                </li>
                <li onClick={() => setActiveSection('Approved Products')}>
                    <FaCheckCircle className="admin-icon" /> Approved Products
                </li>
                <li onClick={() => setActiveSection('Declined Products')}>
                    <FaTimesCircle className="admin-icon" /> Declined Products
                </li>
                <li onClick={() => setActiveSection('Buyers')}>
                    <FaUser className="admin-icon" /> Buyers
                </li>
                <li onClick={() => setActiveSection('Sellers')}>
                    <FaStore className="admin-icon" /> Sellers
                </li>
            </ul>
        </aside>`

        <section className="admin-content">
          {/* TypeScript now understands that activeSection can only be a key from sectionsData */}
          {sectionsData[activeSection]}
        </section>
      </div>
    </div>
  );
};

export default HomePageAdmin;
