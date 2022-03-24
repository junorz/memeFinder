import React from 'react'
import Meme from '../../model/Meme'
import { Divider, Empty } from 'antd'

type States = {
  memes: Array<Meme>
}

class MemeArea extends React.Component<{}, States> {
  states: States = { memes: [] }
  render() {
    return <div>
      <Divider />
      <Empty description="数据正在加载中…"/>
    </div>
  }
}

export default MemeArea
