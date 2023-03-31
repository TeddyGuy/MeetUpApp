import { createContext, FC, useEffect, useState } from 'react'
import { Outlet } from 'react-router-dom'
import './App.css'
import { AuthContext, defaultDecodedJwtValue } from './contexts/AuthContext'
import { MeetUpEventFeedContext } from './contexts/MeetUpEventFeedContext'
import { MeetUpUserSearchResultsContext } from './contexts/MeetUpUserSearchResultsContext'
import DecodedJwt from './model/AuthenticatedUser'
import { MeetUpEvent } from './model/MeetUpEvent'
import MeetUpUser from './model/MeetUpUser'
import meetUpEventService from './service/MeetUpEventService'


const App:FC = ():JSX.Element => {
  const [decodedJwt, setDecodedJwt] = useState<DecodedJwt | undefined>(defaultDecodedJwtValue()); 
  const [meetUpEvents, setMeetUpEvents] = useState<MeetUpEvent[] | undefined>();
  const [meetUpUserSearchResults, setMeetUpUserSearchResults] = useState<MeetUpUser[] | undefined>();
  
  return (
    <AuthContext.Provider value={{decodedJwt, setDecodedJwt}}>
    <MeetUpEventFeedContext.Provider value={{meetUpEvents, setMeetUpEvents}}>
    <MeetUpUserSearchResultsContext.Provider value={{meetUpUserSearchResults, setMeetUpUserSearchResults}}>
      <div>
        <Outlet/>
      </div>
    </MeetUpUserSearchResultsContext.Provider>
    </MeetUpEventFeedContext.Provider>
    </AuthContext.Provider>
    
  )
}

export default App
