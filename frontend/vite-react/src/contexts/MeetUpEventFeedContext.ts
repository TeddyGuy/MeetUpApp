import { createContext } from "react";
import { MeetUpEvent } from "../model/MeetUpEvent";

export interface MeetUpEventFeedContextValues {
    meetUpEvents: MeetUpEvent[] | undefined;
    setMeetUpEvents: React.Dispatch<React.SetStateAction<MeetUpEvent[] | undefined>> | undefined ;
}

export const MeetUpEventFeedContext = createContext<MeetUpEventFeedContextValues>({
    meetUpEvents: undefined,
    setMeetUpEvents: undefined

});