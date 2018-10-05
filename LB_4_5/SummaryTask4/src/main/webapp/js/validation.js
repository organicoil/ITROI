


function validateForm () {

    var password1 = document.getElementById('password');

    var password2 = document.getElementById('password_confirmation');


    if (password1.value !== password2.value) {

        // сообщаем пользователю, можно сделать как угодно

        alert('Пароли не совпадают!');

        // как обычно, т. е. не надо отправлять форму

        return false;

    }

    // проверяем email

    var email = document.getElementById('email');

    // регулярка для проверки, не знаю, работает ли приведенный в примере

    var email_regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";


    if (!email_regexp.test(email.value)) {

        alert('Проверьте email');

        return false;

    }
}
