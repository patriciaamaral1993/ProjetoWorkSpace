<?php

use Illuminate\Http\Request;
use App\Veiculo;
use App\proprietario

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get("tarefa", "TarefaController@index");
Route::get("tarefa/tarefa", "TarefaController@show");
Route::post("tarefa", "TarefaController@store");
Route::put("tarefa/{tarefa}", "TarefaController@update");
Route::delete("tarefa/{tarefa}", "TarefaController@delete");

Route::get("proprietario", "ProprietarioController@index");
Route::get("proprietario/{proprietario}", "ProprietarioController@show");
Route::post("proprietario", "ProprietarioController@store");
Route::put("proprietario/{proprietario}", "ProprietarioController@update");
Route::delete("proprietario/{proprietario}", "ProprietarioController@delete");