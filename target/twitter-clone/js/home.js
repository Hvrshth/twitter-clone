const email = localStorage.getItem('emailid');
const user = email.substring(0, email.indexOf("@"));
document.getElementById('message').textContent = `${user}`;