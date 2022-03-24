import React from 'react'
import { Layout } from 'antd'
import MainArea from './view/main_area'

const { Header, Footer, Content } = Layout

const headerStyle = {
  color: 'white',
}
const contentStyle = {
  margin: '50px',
}

const App = () => (
  <Layout>
    <Header>
      <h2 style={headerStyle}>表情包</h2>
    </Header>
    <Content style={contentStyle}>
      <MainArea />
    </Content>
    <Footer>Copyright &copy; 2021 O2lab.io. All rights reserved.</Footer>
  </Layout>
)

export default App
