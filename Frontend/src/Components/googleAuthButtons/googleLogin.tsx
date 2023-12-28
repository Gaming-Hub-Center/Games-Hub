import React from 'react';
import { GoogleLogin } from 'react-google-login';
import { httpRequest } from '../../Controller/HttpProxy';
import './googleAuthButtonsStyles.css'
import { useNavigate } from 'react-router-dom';
import { UserDTO } from '../../Controller/DTO/user/UserDTO';
import { storeUserData } from '../../CurrentSession';


export function GoogleLoginButton(){
  const navigate = useNavigate();
  const handleGoogleLoginSuccess = (response) => {
    sendTokenToServer(response.tokenId);
  };

  const handleGoogleLoginFailure = (error) => {
    console.error('Google login failed:', error);
  };

  const sendTokenToServer = async (tokenId) => {
    try {
      const response = await httpRequest('POST', '/auth/verify-google-signin', JSON.stringify({ idToken: tokenId }))

      if (response.status == 200) {
        const result = await response.data;
        const responseData = result as UserDTO
        storeUserData(responseData)
        navigate('/')
      } else {
        console.error('Server error:', response.status);
      }
    } catch (error) {
      console.error('Error sending token to server:', error);
    }
  };

  return (
    <div>
      <GoogleLogin
        clientId="922788866859-fv5d49j6cqd2orfai2c1dnte4c8v5ii8.apps.googleusercontent.com"
        buttonText="Login with Google"
        onSuccess={handleGoogleLoginSuccess}
        onFailure={handleGoogleLoginFailure}
        cookiePolicy="single_host_origin"
        isSignedIn={false}
        className='GH-google-button'
      />
    </div>
  );
};
