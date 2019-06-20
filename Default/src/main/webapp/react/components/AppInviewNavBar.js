import React from 'react'
import AppInViewIcon from "./icons/AppInViewIconBase"

export default class AppInViewNavBar extends React.Component {

    render() {
        return (

         <div class="app-in-view-nav-bar">
        <AppInViewIcon name="Project Info" iconImgSrc="/images/icons/appinfo.png"/>
        <AppInViewIcon name="Template Login" iconImgSrc="/images/icons/htmlIcon.png"/>
        <AppInViewIcon name="Oauth Client" iconImgSrc="/images/icons/oauthIcon.png"/>
        <AppInViewIcon name="Service Login" iconImgSrc="/images/icons/serviceLoginIcon.png"/>
        <AppInViewIcon name="Dashboard" iconImgSrc="/images/icons/dashboardIcon.png"/>
         </div> 
        )
      }
    }