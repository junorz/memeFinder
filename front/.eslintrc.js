module.exports = {
  root: true,
  parser: '@typescript-eslint/parser',
  plugins: ['@typescript-eslint'],
  extends: [
    'eslint:recommended',
    'plugin:@typescript-eslint/recommended',
    'prettier',
  ],
  env: {
    browser: true,
    node: true,
    es6: true,
  },
  rules: {
    // 为了能在 React.Component 中使用空对象（{}）的 props 和 states，不对空对象进行检查
    // 详见以下:
    // https://stackoverflow.com/questions/66773897/react-using-typescript-dont-use-as-a-type
    // https://github.com/typescript-eslint/typescript-eslint/blob/main/packages/eslint-plugin/docs/rules/ban-types.md
    '@typescript-eslint/ban-types': [
      'error',
      {
        extendDefaults: true,
        types: {
          '{}': false,
        },
      },
    ],
  },
}
