$(function () {
    $(document).on('click', '.task-link', function () {
        const link = $(this);
        const taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function (response) {
                const code = '<span>Описание дела: ' + response.description + '<span>';
                link.parent().append(code);
            },
            error: function (response) {
                if (response.status === 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });

    const switcher = document.querySelector('.btn');

    switcher.addEventListener('click', function () {
        document.body.classList.toggle('dark-theme')

        const className = document.body.className;
        if (className === "light-theme") {
            this.textContent = "Dark";
        } else {
            this.textContent = "Light";
        }
    });
});