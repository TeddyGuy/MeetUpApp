import { useCallback, useContext, useEffect, useState } from "react"
import { MeetUpEventFeedContext } from "../contexts/MeetUpEventFeedContext";
import { MeetUpEvent } from "../model/MeetUpEvent";
import meetUpEventService from "../service/MeetUpEventService";

export const useRefreshEvents = (...dependencies: any[]) => {
    const { setMeetUpEvents } = useContext(MeetUpEventFeedContext);
    const fetchEvents = useCallback(() => {
        meetUpEventService.search()
        .then((response) => {
            setMeetUpEvents?.(response.data);
        })
        .catch(console.error);
    },[]);

    useEffect(() => {
        fetchEvents()
    },dependencies);

    return fetchEvents;
}