import { createContext } from "react";
import { MeetUpEvent } from "../model/MeetUpEvent";
import MeetUpUser from "../model/MeetUpUser";

export interface MeetUpUserSearchResultsContextValues {
    meetUpUserSearchResults: MeetUpUser[] | undefined;
    setMeetUpUserSearchResults: React.Dispatch<React.SetStateAction<MeetUpUser[] | undefined>> | undefined ;
}

export const MeetUpUserSearchResultsContext = createContext<MeetUpUserSearchResultsContextValues>({
    meetUpUserSearchResults: undefined,
    setMeetUpUserSearchResults: undefined

});