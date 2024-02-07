function checkAndRedirect() {
    // 定义要检查外键约束的表名和ID
    const tableName = '';
    const id = 'your_id';

// 发送AJAX请求
    fetch('/admin/category/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ tab_route, cid })
    })
        .then(response => response.json())
        .then(data => {
            if (data.hasForeignKey) {
                // 显示无法删除的提示信息
                alert('无法删除，存在外键约束');
            } else {
                // 显示可以正常删除的提示信息或执行删除操作
                alert('可以正常删除');
                // 执行删除操作
                deleteRecord(tab_route, cid);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}