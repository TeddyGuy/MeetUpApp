import { FC, useState } from 'react'
import './App.css'
import SignInFormContainer from './container/SignInFormContainer'
import { SignUpFormContainer } from './container/SignUpFormContainer'

const App:FC = ():JSX.Element => {

  return (
    <div>
      <h1>Sign Up</h1>
      <SignUpFormContainer />
      <h1>Sign In</h1>
      <SignInFormContainer />
    </div>
  )
}

export default App
