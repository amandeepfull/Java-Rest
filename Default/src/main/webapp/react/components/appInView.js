import React from 'react';
import AppInViewNavBar from "./AppInviewNavBar";
import AppInBottomView from "./AppInBottomView";

export default class AppInView extends React.Component {

    render() {
        return (

         <div className="app-in-view">
        <AppInViewNavBar/>
        <AppInBottomView/>
         </div> 
        )
      }
    }