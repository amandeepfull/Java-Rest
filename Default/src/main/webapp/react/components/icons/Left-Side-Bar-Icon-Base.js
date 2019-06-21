import React from 'react'

export default class LeftSideBarIconBase extends React.Component {
constructor(props){
    super(props)
}
    render() {

        return (
            <a className="left-side-bar-icon-base" href="#"><img src={this.props.iconImgSrc} width="20px" /><span className="left-side-bar-icon-title" title={this.props.name} >{this.props.name}</span></a>
        )
      }
    }