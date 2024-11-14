const baseUrl = 'http://localhost:8080/series';

function getValoraciones() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", `${baseUrl}/all`, false);  // false hace sincrona la request
    xhr.send();
    const datos = JSON.parse(xhr.responseText);


    if (xhr.status === 200) {
        let text = '';
        for(i=0;i<datos.length;i++) {
            let serie = datos[i];
            text += '<span>'+serie.nombre+'</span><span>'+serie.plataformaStreaming+'</span><br>';
            for(j=0;j<serie.valoraciones.length;j++) {
                let valoracion = serie.valoraciones[j];
                text += '<span class="valoracion">'+valoracion.autor+'</span>' +
                    '<span class="valoracion">'+valoracion.valoracion+'</span><br>';
            }
        }
        document.getElementById('allSeriesResult').innerHTML = text;
    } else {
        document.getElementById('allSeriesResult').innerText = 'Error fetching all series';
    }
}

function getTopSeries() {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", `${baseUrl}/top`, false);  // false hace sincrona la request
    xhr.send();

    if (xhr.status === 200) {
        let text = '<table><thead>' +
            '<th>ID</th><th>Caratula</th><th>Nombre</th><th>Plataforma Streaming</th><th>Sinopsis</th>' +
            '<th>Valoracion Media</th></thead><tbody>';
        console.log(document.getElementById('topSeriesResult').innerHTML);

        const datos = JSON.parse(xhr.responseText);
        for(i=0;i<datos.length;i++) {
            let serie = datos[i];
            text += '<tr><td>'+serie.id+'</td><td><img src="'+serie.caratula+'"/><td>'+serie.nombre+'</td>' +
                '<td>'+serie.plataformaStreaming+'</td><td>'+serie.sinopsis+'</td>' +
                '<td>'+serie.valoracionMedia+'</td></tr>';
        }
        text += '</tbody></table>';
        document.getElementById('topSeriesResult').innerHTML = text;

        console.log(document.getElementById('topSeriesResult').innerHTML);
    } else {
        document.getElementById('topSeriesResult').innerText = 'Error fetching top series';
    }
}

function addSerie() {
    const nombre = document.getElementById('nombre').value;
    const caratula = document.getElementById('caratula').value;
    const plataformaStreaming = document.getElementById('plataformaStreaming').value;
    const sinopsis = document.getElementById('sinopsis').value;

    const serie = {
        nombre: nombre,
        caratula: caratula,
        plataformaStreaming: plataformaStreaming,
        sinopsis: sinopsis
    };

    const xhr = new XMLHttpRequest();
    xhr.open("POST", `${baseUrl}/add`, false);  // false hace sincrona la request
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(serie));

    if (xhr.status === 200) {
        document.getElementById('addSeriesResult').innerText = 'Serie añadida correctamente!';
    } else {
        document.getElementById('addSeriesResult').innerText = 'Error añadiendo serie';
    }
}

function addValoracion() {
    const idSerie = parseInt(document.getElementById('idSerie').value);
    const valoracion = parseFloat(document.getElementById('valoracion').value);
    const autor = document.getElementById('autor').value;

    const valoracionData = {
        autor: autor,
        valoracion: valoracion
    };

    const xhr = new XMLHttpRequest();
    xhr.open("POST", `${baseUrl}/valoracion?idSerie=${idSerie}`, false);  // false hace sincrona la request
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(valoracionData));

    if (xhr.status === 200) {
        document.getElementById('addValoracionResult').innerText = 'Valoracion añadida correctamente!!';
    } else {
        document.getElementById('addValoracionResult').innerText = 'Error añadiendo valoracion';
    }
}
