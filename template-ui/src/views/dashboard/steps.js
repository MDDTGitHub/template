const steps = [
  {
    element: '#hamburger-container',
    popover: {
      title: '边栏框',
      description: '打开和关闭边栏',
      position: 'bottom'
    }
  },
  {
    element: '#breadcrumb-container',
    popover: {
      title: '当前页',
      description: '指示当前页面位置',
      position: 'bottom'
    }
  },
  {
    element: '#header-search',
    popover: {
      title: '页面搜索',
      description: '页面搜索，快速导航',
      position: 'left'
    }
  },
  {
    element: '#screenfull',
    popover: {
      title: '全屏',
      description: '将页面设置为全屏',
      position: 'left'
    }
  },
  {
    element: '#size-select',
    popover: {
      title: '布局大小',
      description: '切换系统布局大小',
      position: 'left'
    }
  },
  {
    element: '#tags-view-container',
    popover: {
      title: '标签页',
      description: '您访问的页面的历史记录',
      position: 'bottom'
    },
    padding: 0
  }
]

export default steps
