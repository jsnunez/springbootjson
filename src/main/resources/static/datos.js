document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('registrationForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        const data = {
            nombre: formData.get('nombre'),
            apellido: formData.get('apellido'),
            edad: formData.get('edad'),
            cedula: formData.get('cedula')
        };

        fetch('/api/crearusuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            alert('Usuario registrado exitosamente!');
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    });

    document.getElementById('searchForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const cedula = document.getElementById('searchCedula').value;

        fetch(`/api/buscarusuario?cedula=${cedula}`)
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('searchResult');
                if (data) {
                    resultDiv.innerHTML = `
                        <h2>Resultado de la búsqueda:</h2>
                        <p>Nombre: ${data.nombre}</p>
                        <p>Apellido: ${data.apellido}</p>
                        <p>Edad: ${data.edad}</p>
                        <p>Cédula: ${data.cedula}</p>
                    `;
                } else {
                    resultDiv.innerHTML = '<p>Usuario no encontrado.</p>';
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });

    document.getElementById('viewAllBtn').addEventListener('click', function() {
        fetch('/api/verusuarios')
            .then(response => response.json())
            .then(data => {
                const allUsersDiv = document.getElementById('allUsers');
                allUsersDiv.innerHTML = '<h2>Todos los usuarios:</h2>';
                for (const [key, user] of Object.entries(data)) {
                    allUsersDiv.innerHTML += `
                        <p>
                            Cédula: ${key}<br>
                            Nombre: ${user.nombre}<br>
                            Apellido: ${user.apellido}<br>
                            Edad: ${user.edad}
                        </p>
                    `;
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });
});
