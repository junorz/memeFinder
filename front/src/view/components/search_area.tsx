import React from 'react'
import { Input, Select, Row, Col } from 'antd'
import Category from '../../model/Category'
import categoryRepository from '../../repository/category_repository'

const { Option } = Select

type States = {
  categories: Array<Category>
  isPulldownLoading: boolean
}

const pulldownStyle = {
  width: '100%',
}

class SearchArea extends React.Component<{}, States> {
  // Property initializer语法，有了这个可以不用定义构造器。
  // 详见：https://babeljs.io/docs/en/babel-plugin-proposal-class-properties
  state: States = { categories: [], isPulldownLoading: true }

  componentDidMount() {
    // 为了避免将 componentDidMount 声明为 async 导致一些意料之外的错误，这里不使用 await
    categoryRepository
      .getAllCategories()
      .then((categories) => this.initializePullDown(categories))
  }

  render() {
    return (
      <Input.Group>
        <Row>
          <Col md={3} xs={10}>
            <Select
              style={pulldownStyle}
              loading={this.state.isPulldownLoading}
              defaultValue="全部类别"
            >
              <Option value="all" key="all">
                全部类别
              </Option>
              {this.state.categories?.map((category) => {
                return (
                  <Option value={category.id} key={category.id}>
                    {category.name}
                  </Option>
                )
              })}
            </Select>
          </Col>
          <Col md={21} xs={12}>
            <Input.Search allowClear placeholder="搜索" />
          </Col>
        </Row>
      </Input.Group>
    )
  }

  initializePullDown(categories: Array<Category>) {
    this.setState({
      categories: categories,
      isPulldownLoading: false,
    })
  }
}

export default SearchArea
