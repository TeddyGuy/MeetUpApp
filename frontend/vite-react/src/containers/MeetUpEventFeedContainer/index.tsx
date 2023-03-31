import { FC, useContext, useEffect } from "react";
import { AuthContext, AuthContextValues } from "../../contexts/AuthContext";
import { useRefreshEvents } from "../../hooks/useRefreshEvents";
import { MeetUpEvent } from "../../model/MeetUpEvent";
import meetUpEventService from "../../service/MeetUpEventService";
import { MeetUpEventSearchFormContainer } from "../MeetUpEventSearchFormContainer";

interface Props {
    events: MeetUpEvent[] | undefined;
}
export const MeetUpEventFeedContainer: FC<Props> = ({events}) => {
    const {decodedJwt} = useContext<AuthContextValues>(AuthContext); 
    const refreshEvents = useRefreshEvents();
    const handleJoinEvent = (event: MeetUpEvent) => {
        meetUpEventService.join(event.id)
        .then(() => {
            refreshEvents();
        })
        .catch(console.error);
    }
    return(
        <div>
            {
                events?.map(
                    (event, key) => (
                        <div key={key}>
                            {JSON.stringify(event)}
                            {decodedJwt ? <button onClick={() => handleJoinEvent(event)}>Join</button> : null}
                        </div>
                    )
                )
            }
        </div>
    );
}

export default MeetUpEventFeedContainer;