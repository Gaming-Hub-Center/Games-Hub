import React from 'react';
import { GoogleLogin } from 'react-google-login';
import { httpRequest } from '../../Controller/HttpProxy';
import './googleAuthButtonsStyles.css'
import { useNavigate } from 'react-router-dom';
import {getCurrentProductPage, setRole, setToken} from "../../session/CurrentSession";
import {ProductType} from "../../enums/ProductType";


export function GoogleSignUpButtonSeller(){
  const navigate = useNavigate();
  const handleGoogleSignupSuccess = (response) => {
    sendTokenToServer(response.tokenId);
  };

  const handleGoogleSignupFailure = (error) => {
    console.error('Google Signup failed:', error);
  };

  const sendTokenToServer = async (tokenId) => {
    try {
      const response = await httpRequest('POST', '/auth/verify-google-signup/seller', JSON.stringify({ idToken: tokenId }))

      if (response.status == 200) {
        const token = response.data as string
        const role = JSON.parse(atob(token.split(".")[1])).role
        setRole(role)
        setToken(token)
        if (role === 'ADMIN')
          navigate('/admin/dashboard')
        else if (role === 'SELLER')
          navigate('/seller/catalog')
        else
          navigate(`/buyer/home/${getCurrentProductPage() === ProductType.PHYSICAL ? 'accessories' : 'games'}`)
        console.log(role)
        console.log(token)
      } else {
        console.log(response.data.message)
      }
    } catch (error) {
      console.log(error.response.data.message)
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
