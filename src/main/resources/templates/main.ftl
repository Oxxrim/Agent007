<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Agent 007</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Agent 007</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <#--<ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Main</a>
            </li>
            <li>
                <a class="nav-link" href="/graph">Graphs</a>
            </li>
        </ul>-->
</nav>
<div class="container mt-5">
    <div class="form-row">
        <div class="form-group col-md-10">
            <form method="post" action="/" class="form-inline">
                <input type="number"  min="1" name="tool" class="form-control" value="${tool?if_exists}" placeholder="Input count of spy tools"/>
                <input type="number"  min="1" name="barrier" class="form-control ml-2" value="${barrier?if_exists}" placeholder="Input count of barriers"/>
                <button type="submit" class="btn btn-primary ml-2">Generate matrix</button>
            </form>
            <br>
            <a href="/file" class="mt-4"><button class="btn btn-primary">Input from File</button></a>
        </div>
    </div>
</div>
<div class="container mt-2">
    <table class="table mt-2">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Tools</th>
            <th scope="col">Barriers</th>
        </tr>
        </thead>
        <tbody>
        <#list matrix?if_exists as row>
            <tr>
                <td scope="row">Tool ${row?index + 1} : </td>
                <td scope="row">${row}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<div class="container mt-2">
    <h3>${check?if_exists}</h3>
</div>
<div class="container mt-2">
    <a href="/greedy"><button class="btn btn-primary">Greedy algorithm</button></a>
    <a href="/genetic"><button class="btn btn-primary">Genetic algorithm</button></a>
    <a href="/bee"><button class="btn btn-primary">Bee algorithm</button></a>
</div>
<div class="container mt-2">
    <h3>${answer?if_exists}</h3>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>