import { useState } from 'react';
import { FaCheckCircle, FaFileAlt, FaHourglassHalf, FaHourglassStart, FaStore, FaTimesCircle, FaUser } from 'react-icons/fa';
import './AdminDashboard.css';
import NavBarAdmin from '../../Components/admin/NavBarAdmin';
import PendingProductsComponent from '../../Components/admin/PendingProducts';
import {BuyerView} from "./BuyerView";
import {SellerView} from "./SellerView";

import {Navigate} from "react-router-dom";
import {getId} from "../../session/CurrentSession";

import AdminProductsComponent from '../../Components/admin/PendingProducts';

// Define a type for the valid section keys
type Section =   'Pending Physical Products' |  'Pending Digital Products' | 
                'Approved Physical Products' | 'Approved Digital Products' |
                'Declined Physical Products' | 'Declined Digital Products' |
                'Buyers' | 'Sellers' | 'Admins';

// Define a type for the sections data with the above keys
type SectionsData = {
  [key in Section]: JSX.Element;
};

// Define your sectionsData with the corresponding type
const sectionsData: SectionsData = {
    "Pending Physical Products": <AdminProductsComponent
                                    productType="physical"
                                    iconVisibility={{ showApprove: true, showDecline: true, showDetails: true }}
                                    status='Pending'/>,
    "Pending Digital Products": <AdminProductsComponent
                                    productType="digital"
                                    iconVisibility={{ showApprove: true, showDecline: true, showDetails: true }}
                                    status='Pending'/>,
    "Approved Physical Products": <AdminProductsComponent
                                    productType="physical"
                                    iconVisibility={{ showApprove: false, showDecline: false, showDetails: true }}
                                    status='Approved'/>,
    "Approved Digital Products": <AdminProductsComponent
                                    productType="digital"
                                    iconVisibility={{ showApprove: false, showDecline: false, showDetails: true }}
                                    status='Approved'/>,
    "Declined Physical Products": <AdminProductsComponent
                                    productType="physical"
                                    iconVisibility={{ showApprove: false, showDecline: false, showDetails: true }}
                                    status='Declined'/>,
    "Declined Digital Products": <AdminProductsComponent
                                    productType="digital"
                                    iconVisibility={{ showApprove: false, showDecline: false, showDetails: true }}
                                    status='Declined'/>,

    Buyers: <BuyerView></BuyerView>,
    Sellers: <Navigate to={'/admin/view/sellers'}></Navigate>,
    Admins: <Navigate to={`/admin/view/admins/${getId()}`}></Navigate>
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
                <li onClick={() => setActiveSection('Approved Physical Products')}>
                    <FaCheckCircle className="admin-icon" /> Approved Physical Products
                </li>
                <li onClick={() => setActiveSection('Approved Digital Products')}>
                    <FaCheckCircle className="admin-icon" /> Approved Digital Products
                </li>
                <li onClick={() => setActiveSection('Declined Physical Products')}>
                    <FaTimesCircle className="admin-icon" /> Declined Physical Products
                </li>
                <li onClick={() => setActiveSection('Declined Digital Products')}>
                    <FaTimesCircle className="admin-icon" /> Declined Digital Products
                </li>
                <li onClick={() => setActiveSection('Buyers')}>
                  <FaUser className="admin-icon"/> Buyers
                </li>
                <li onClick={() => setActiveSection('Sellers')}>
                  <FaStore className="admin-icon"/> Sellers
                </li>
                <li onClick={() => setActiveSection('Admins')}>
                  <FaStore className="admin-icon"/> Admins
                </li>
            </ul>
        </aside>`

        <section className="admin-content">
          {sectionsData[activeSection]}
        </section>
      </div>
    </div>
  );
};

export default HomePageAdmin;
