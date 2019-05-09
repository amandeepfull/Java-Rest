import React from 'react'
import LeftSideBar from './left-side-bar';
import RightSideBar from './right-side-bar';

export default class App extends React.Component {



  render() {
    return (
    <React.Fragment>  
     <h1> OauthCater.</h1>
     <div id="app">
     
     <LeftSideBar/>
     <RightSideBar/>
     </div>
     </React.Fragment>
    )
  }
}
