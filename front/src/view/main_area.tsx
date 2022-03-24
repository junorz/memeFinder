import React from 'react'
import SearchArea from './components/search_area'
import MemeArea from './components/meme_area'

class MainArea extends React.Component {
  render() {
    return (
      <div>
        <SearchArea />
        <MemeArea />
      </div>
    )
  }
}

export default MainArea
