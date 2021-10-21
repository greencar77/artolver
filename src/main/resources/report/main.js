"use strict";

function init() {
    let div = document.getElementById('projects');

    let el = document.createElement('ol');
    div.appendChild(el);
    for (const p of proj) {
        let projEl = document.createElement('li');
        projEl.textContent = p.name;
        el.appendChild(projEl);
    }
}