import { Field, Form, Formik, FormikHelpers, FormikValues } from "formik";
import { FormEvent, useContext, useEffect, useState } from "react";
import { MeetUpEventFeedContextValues, MeetUpEventFeedContext } from "../../contexts/MeetUpEventFeedContext";
import MeetUpEventSearchForm from "../../model/forms/MeetUpEventSearchForm";
import meetUpEventService from "../../service/MeetUpEventService";

export const MeetUpEventSearchFormContainer = () => {
    const [form, setForm] = useState<MeetUpEventSearchForm>({query:''})
    const { setMeetUpEvents } = useContext<MeetUpEventFeedContextValues>(MeetUpEventFeedContext);
    useEffect(() => {
        meetUpEventService.search(form)
        .then((response) => {
            setMeetUpEvents?.(response.data);
        })
        .catch(console.error);
    },[form]);
    return (
        <form>
            <input type="text" name="query" value={form.query}
                onChange={(e) => setForm({...form, query: e.target.value})}
            />
        </form>
        
    );
}