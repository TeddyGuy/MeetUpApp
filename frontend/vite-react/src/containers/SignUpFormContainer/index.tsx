import { Field, Form, Formik, FormikHelpers } from "formik";
import { FC } from "react";
import { useRefreshEvents } from "../../hooks/useRefreshEvents";
import SignUpForm from "../../model/forms/SignUpForm";
import SignUpRequest from "../../model/payload/request/SignUpRequest";
import authService from "../../service/AuthService";

export const SignUpFormContainer: FC = ():JSX.Element => {
  const refreshEvents = useRefreshEvents();
  return(
    <Formik
      initialValues={{
        email: '',
        name: '',
        password: '',
        passwordVerification: '',
      }}
      onSubmit={(
        values: SignUpForm,
        { setSubmitting }: FormikHelpers<SignUpForm>
      ) => {
          const payload: SignUpRequest = {
              email: values.email,
              name: values.name,
              rawPassword: values.password
          }
          authService.signUp(payload)
          .then(() => {
            refreshEvents();
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

        <label htmlFor="name">Name</label>
        <Field id="name" name="name" placeholder="John" />

        <label htmlFor="password">Password</label>
        <Field id="password" name="password" placeholder="Doe" />

        <Field id="passwordVerification" name="passwordVerification" placeholder="Doe" />

        <button type="submit">Sign Up</button>
      </Form>
    </Formik>
  );
}

export default SignUpFormContainer;