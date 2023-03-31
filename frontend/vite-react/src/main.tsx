import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import { BrowserRouter as Router, Route, Routes }  from 'react-router-dom'
import HomeView from './view/HomeView'

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/" element={<App/>}>
          <Route index element={<HomeView/>}/>
        </Route>
      </Routes>
    </Router>
  </React.StrictMode>,
)
