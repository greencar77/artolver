"use strict";

function init() {
    let focusProjId = window.location.hash.substring(1);
    let project = getProject(focusProjId);

    let titleEl = document.getElementById('title');
    titleEl.textContent = project.name;

    let projectUrlEl = document.getElementById('projectUrl');
    let hrefEl = document.createElement('a');
    hrefEl.setAttribute('href', project.projectUrl);
    hrefEl.textContent = '[' + project.projectUrl + ']';
    projectUrlEl.appendChild(hrefEl);

    let depEl = document.getElementById('deps');
    for (const d of project.dependencies) {
        let depItemEl = document.createElement('li');
        depItemEl.textContent = d;
        depEl.appendChild(depItemEl);
    }
}

function getProject(projId) {
    for (const p of proj) {
        if (p.id == projId) {
            return p;
        }
    }
}