import { AxiosResponse } from "axios";
import { Field, Form, Formik, FormikHelpers } from "formik";
import { FC, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext, AuthContextValues } from "../../contexts/AuthContext";
import SignInForm from "../../model/forms/SignInForm";
import SignInRequest from "../../model/payload/request/SignInRequest";
import JwtResponse from "../../model/payload/response/JwtResponse";
import authService from "../../service/AuthService";
import jwt_decode from "jwt-decode";
import DecodedJwt from "../../model/AuthenticatedUser";
import { useRefreshEvents } from "../../hooks/useRefreshEvents";

export const SignInFormContainer: FC = ():JSX.Element => {
  const {setDecodedJwt} = useContext<AuthContextValues>(AuthContext); 
  const refreshEvents = useRefreshEvents();
  return(
    <Formik
      initialValues={{
        email: '',
        password: '',
      }}
      onSubmit={(values: SignInForm,
        { setSubmitting }: FormikHelpers<SignInForm>
      ) => {
          const payload: SignInRequest = {
              email: values.email,
              rawPassword: values.password
          }
          authService.signIn(payload)
          .then((response: AxiosResponse<JwtResponse>) => {
              const jwt: string = response.data.jwt;
              const decodedJwt: DecodedJwt = jwt_decode(jwt);
              localStorage.setItem("jwt", jwt);
              setDecodedJwt?.(decodedJwt);
              setSubmitting(false);
              refreshEvents();
          })
          .catch(console.error);
      }}
    >
      <Form>
        <label htmlFor="email">Email</label>
        <Field
          id="email"
          name="email"
          placeholder="john@acme.com"
          type="email"
        />

        <label htmlFor="password">Password</label>
        <Field id="password" name="password" placeholder="Doe" />

        <button type="submit">Sign In</button>
      </Form>
    </Formik>
  );
}

export default SignInFormContainer;