import { Field, Form, Formik, FormikHelpers, FormikValues } from "formik";
import { FormikDatePickerField } from "../../components/FormikDatePicker/FormitDatePicker";
import { useRefreshEvents } from "../../hooks/useRefreshEvents";
import SaveMeetUpEventForm from "../../model/forms/SaveMeetUpEventForm";
import { MeetUpEventSaveRequest } from "../../model/payload/request/MeetUpEventSaveRequest";
import meetUpEventService from "../../service/MeetUpEventService";

export const NewEventFormContainer = () => {
    const refreshEvents = useRefreshEvents();
    return(
        <Formik
            initialValues={
                {
                    name:"",
                    date: new Date()
                }
            } 
            onSubmit={(values: SaveMeetUpEventForm, {setSubmitting}: FormikHelpers<SaveMeetUpEventForm> ) => {
                const payload: MeetUpEventSaveRequest = {
                    name: values.name,
                    date: values.date
                }
                meetUpEventService.save(payload)
                .then(() => {
                    refreshEvents();
                    setSubmitting(false)
                })
                .catch(console.error)
            }}>
            <Form>
                <label htmlFor="name">Name</label>
                <Field
                    id="name"
                    name="name"
                    placeholder="The Best Event"
                />
                <label htmlFor="date">Date</label>
                <FormikDatePickerField name="date"/>
                <button type="submit">Create</button>
            </Form>
        </Formik>
    );
}