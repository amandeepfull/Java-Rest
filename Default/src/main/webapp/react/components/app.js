import React from 'react'
import TopNavBar from "./Top-Nav-Bar"
import MainBottomView from "./MainBottomView"

export default class App extends React.Component {



  render() {
    return (
      <React.Fragment>
      <div className="overlay"></div>
     <div className="oauthcater">
     <TopNavBar/>
    <MainBottomView/>
     </div>
     </React.Fragment>
    )
  }
}
