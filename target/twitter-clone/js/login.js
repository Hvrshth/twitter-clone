document.getElementById('loginForm').addEventListener('submit', function(event){
    event.preventDefault();
    const email = document.getElementById('emailaddress').value;
    localStorage.setItem('emailid', email);
    window.location.href = 'home.html';
});