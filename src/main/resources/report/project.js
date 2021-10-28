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
        let depSpan = document.createElement('span');
        depSpan.setAttribute("onclick", "clickDep(this);");
        depSpan.textContent = d.name;
        depItemEl.appendChild(depSpan);
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

function clickDep(element) {
    let depId = element.textContent;
    let typesElem = document.getElementById('types');
    typesElem.textContent = '';
    let dep = getDep(depId);
    dep.types.forEach(t => {
        let el = document.createElement('li');
        el.textContent = t;
        typesElem.appendChild(el);
    });
}

function getDep(depId) {
    return dependencies.filter(d => d.name == depId)[0];
}