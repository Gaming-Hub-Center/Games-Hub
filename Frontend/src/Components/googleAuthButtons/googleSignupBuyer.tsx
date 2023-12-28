import React from 'react';
import { GoogleLogin } from 'react-google-login';
import { httpRequest } from '../../Controller/HttpProxy';
import { useNavigate } from 'react-router-dom';
import { storeUserData } from '../../CurrentSession';
import { UserDTO } from '../../Controller/DTO/user/UserDTO';
import './googleAuthButtonsStyles.css'


export function GoogleSignUpButtonBuyer(){
  const navigate = useNavigate();
  const handleGoogleSignupSuccess = (response) => {
    sendTokenToServer(response.tokenId);
  };

  const handleGoogleSignupFailure = (error) => {
    console.error('Google Signup failed:', error);
  };

  const sendTokenToServer = async (tokenId) => {
    try {
      const response = await httpRequest('POST', '/auth/verify-google-signup/buyer', JSON.stringify({ idToken: tokenId }))

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
        buttonText="Signup with Google"
        onSuccess={handleGoogleSignupSuccess}
        onFailure={handleGoogleSignupFailure}
        cookiePolicy="single_host_origin"
        isSignedIn={false}
        className='GH-google-button'
      />
    </div>
  );
};
