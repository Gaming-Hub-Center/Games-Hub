import React, { useState } from 'react'
import axios from 'axios'

const SignIn: React.FC = () => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [errorMessage, setErrorMessage] = useState('')

    const handleSignIn = async (e: React.FormEvent) => {
        e.preventDefault()
        setErrorMessage('') // Reset error message

        try {
            const response = await axios.post('http://localhost:8080/signin', {
                email,
                password
            })

            if (response.data) {
                console.log("Authentication successful")
                // Handle successful authentication (e.g., redirect, store JWT, etc.)
            } else {
                console.log("Authentication failed")
                setErrorMessage('Authentication failed')
            }
        } catch (error) {
            console.error("Error during sign in:", error)
            setErrorMessage('An error occurred during sign in')
        }
    }

    return (
        <div>
            <form onSubmit={handleSignIn}>
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email"
                />
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Password"
                />
                <button type="submit">Sign In</button>
            </form>
            {errorMessage && <div>{errorMessage}</div>}
        </div>
    )
}

export default SignIn