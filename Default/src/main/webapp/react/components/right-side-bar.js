import React from 'react'

import AddApp from './add_app';

export default class RightSideBar extends React.Component {

        constructor(props){
            super(props);
            this.state = {
                normalView : true,
                addAppView : false
            }

            this.handlick = this.handlick.bind(this);
        }

        handlick(){

            this.setState({
                normalView : false,
                addAppView : true
            });
        }

  render() {

    if(this.state.addAppView)
    return <AddApp/>

    if(this.state.normalView)
    return (
     <div id="right-side-view">
     <button type="button" class="btn btn-primary" onClick={this.handlick}>Add App</button>
     </div>
    )
  }
}
