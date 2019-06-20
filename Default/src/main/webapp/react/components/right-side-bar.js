import React from 'react'
import AppInView from './appInView'
import AppInTopView from './App-In-top-view'

export default class RightSideBar extends React.Component {
        constructor(props){
            super(props)
        }
    render() {
        return (
         <div class="right-side-bar">
        <AppInTopView/>
         <AppInView/>
         </div> 
        )
      }
    }