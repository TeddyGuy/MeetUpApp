import { FC, useContext, useEffect, useState } from "react"
import { AuthFormsContainer } from "../../containers/AuthFormsContainer";
import MeetUpEventFeedContainer from "../../containers/MeetUpEventFeedContainer";
import { MeetUpEventSearchFormContainer } from "../../containers/MeetUpEventSearchFormContainer";
import { MeetUpUserSearchFormContainer } from "../../containers/MeetUpUserSearchFormContainer";
import { NewEventFormContainer } from "../../containers/NewEventFormContainer";
import { AuthContext, AuthContextValues } from "../../contexts/AuthContext";
import { MeetUpEventFeedContext, MeetUpEventFeedContextValues } from "../../contexts/MeetUpEventFeedContext";
import { useRefreshEvents } from "../../hooks/useRefreshEvents";
import authService from "../../service/AuthService";
import meetUpEventService from "../../service/MeetUpEventService";

export const HomeView:FC = ():JSX.Element => {
    const {decodedJwt, setDecodedJwt} = useContext<AuthContextValues>(AuthContext);
    const { meetUpEvents } = useContext<MeetUpEventFeedContextValues>(MeetUpEventFeedContext);
    const signOutHandler = () => {
        authService.signOut(setDecodedJwt);
    }
    useRefreshEvents(decodedJwt);
    return(
        <div>
            {decodedJwt ? (
                <div>
                    <h1>Bonjour {decodedJwt.name}</h1>
                    <button onClick={signOutHandler}>
                        Sign Out
                    </button>
                </div>
            ) : ''}
            
            {!decodedJwt ? <AuthFormsContainer/> : ''}
            {decodedJwt ? <NewEventFormContainer/> : ''}
            <MeetUpEventSearchFormContainer />
            <MeetUpEventFeedContainer events={meetUpEvents}/>
            <MeetUpUserSearchFormContainer/>
        </div>  
    );
}

export default HomeView;