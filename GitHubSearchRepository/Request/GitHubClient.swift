//
//  GitHubClient.swift
//  GitHubSearchRepository
//
//  Created by SHIKI TAKAHASHI on 2017/11/09.
//  Copyright © 2017年 SHIKI TAKAHASHI. All rights reserved.
//

import Foundation

class GitHubClient {
    private let session: URLSession = {
        let configuration = URLSessionConfiguration.default
        let session = URLSession(configuration: configuration)
        return session
    }()
    
    func send<Request: GitHubRequest>(
        request: Request,
        completion: (Result<Request.Response, GitHubClientError>) ->
        Void) {
    }
}
