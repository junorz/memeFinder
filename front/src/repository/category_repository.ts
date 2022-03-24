import axios from 'axios'
import Category from '../model/Category'

const getAllCategories = async (): Promise<Array<Category>> => {
  const response = await axios.get('/category/all')
  const categoryList: Array<Category> = []
  response.data.forEach((e: Category) =>
    categoryList.push({
      id: e.id,
      name: e.name,
    }),
  );
  return categoryList
}

const categoryRepository = {
  getAllCategories,
}

export default categoryRepository
