import { Field, Form, Formik, FormikHelpers, FormikValues } from "formik";
import { FormEvent, useContext, useEffect, useState } from "react";
import { MeetUpEventFeedContextValues, MeetUpEventFeedContext } from "../../contexts/MeetUpEventFeedContext";
import { MeetUpUserSearchResultsContext, MeetUpUserSearchResultsContextValues } from "../../contexts/MeetUpUserSearchResultsContext";
import MeetUpEventSearchForm from "../../model/forms/MeetUpEventSearchForm";
import MeetUpUser from "../../model/MeetUpUser";
import meetUpEventService from "../../service/MeetUpEventService";
import meetUpUserService from "../../service/MeetUpUserService";

export const MeetUpUserSearchFormContainer = () => {
    const [form, setForm] = useState<MeetUpEventSearchForm>({query:''})
    const [meetUpUserSearchResults, setMeetUpUserSearchResults] = useState<MeetUpUser[]>();
    useEffect(() => {
        meetUpUserService.search(form)
        .then((response) => {
            setMeetUpUserSearchResults(response.data);
        })
        .catch(console.error);
    },[form]);
    return (
        <div>
            <form>
                <input type="text" name="query" value={form.query}
                    onChange={(e) => setForm({...form, query: e.target.value})}
                />
            </form>
            {
                meetUpUserSearchResults?.map(
                    (user, key) => (
                        <div key={key}>
                            {JSON.stringify(user)}
                        </div>
                    )
                )
            }
        </div>
        
        
    );
}