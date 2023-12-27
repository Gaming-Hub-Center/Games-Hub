import { useNavigate } from 'react-router-dom';
import './NavBarAdmin.css'; // Make sure you have the CSS file for styling
import { clearCurrentSession, getName } from '../../CurrentSession';

const NavBarAdmin = () => {
  const navigate = useNavigate();
  const adminName = getName(); 
  const handleSignOut = () => {
    clearCurrentSession()
    navigate('/signin');
  };

  return (
    <nav className="admin-navbar">
      <h1>Admin Dashboard</h1>
      <div className="admin-navbar-right">
        <span className="admin-signed-in-as">Signed in as {adminName}</span>
        {/* Sign out button */}
        <button onClick={handleSignOut} className="admin-sign-out-button">
          Sign Out
        </button>
      </div>
    </nav>
  );
};

export default NavBarAdmin;