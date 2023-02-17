import { AxiosResponse } from "axios";
import { Field, Form, Formik, FormikHelpers } from "formik";
import { FC } from "react";
import SignInForm from "../../model/forms/SignInForm";
import SignInRequest from "../../model/payload/request/SignInRequest";
import JwtResponse from "../../model/payload/response/JwtResponse";
import authService from "../../service/AuthService";

export const SignInFormContainer: FC = ():JSX.Element => {
    return(
        <Formik
        initialValues={{
          email: '',
          password: '',
        }}
        onSubmit={(
          values: SignInForm,
          { setSubmitting }: FormikHelpers<SignInForm>
        ) => {
            const payload: SignInRequest = {
                email: values.email,
                rawPassword: values.password
            }
            authService.signIn(payload)
            .then((response: AxiosResponse<JwtResponse>) => {
                console.log("Authenticated. JWT is : " + response.data.jwt);
                setSubmitting(false)
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